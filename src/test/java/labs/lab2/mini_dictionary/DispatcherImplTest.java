package labs.lab2.mini_dictionary;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class DispatcherImplTest {
    private final PrintStream original = System.out;
    private final Dictionary dictionary = new DictionaryImpl();
    private final FileDictionaryProvider provider = new FileDictionaryProviderImpl(dictionary);
    private final InteractionObserver observer = spy(new InteractionObserverImpl(provider));
    private final Dispatcher dispatcher = new DispatcherImpl(observer);
    private ByteArrayOutputStream mock = new ByteArrayOutputStream();

    public DispatcherImplTest() throws IOException {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        final var file = new File("dictionary.tsv");
        Files.delete(Path.of(file.getAbsolutePath()));
    }

    @Before
    public void setUp() {
        mock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mock));
    }

    @After
    public void tearDown() {
        System.setOut(original);
        dictionary.clear();
    }

    @Test
    public void testDispatchEmptyWord() throws IOException {
        dispatcher.dispatch(InteractionEvents.EMPTY_INPUT);
        final int wantedNumberOfInvocations = 1;
        verify(observer, times(wantedNumberOfInvocations)).onEmptyInput();
        final String expectedMessage = "Пусто. Введите заново.";
        assertEquals(expectedMessage, mock.toString().strip());
    }

    @Test
    public void testDispatchSaveDict() throws IOException {
        final var original = System.in;
        System.setIn(new ByteArrayInputStream("Y".getBytes()));
        dispatcher.dispatch(InteractionEvents.SAVE_DICT);
        final int wantedNumberOfInvocations = 1;
        verify(observer, times(wantedNumberOfInvocations)).onSaveDict();
        final String expectedMessage = "В словарь были внесены изменения. Введите Y или y для сохранения перед выходом.";
        assertEquals(expectedMessage, mock.toString().strip());
        System.setIn(original);
    }

    @Test
    public void testDispatchUnknownWordSave() {
        final var original = System.in;
        final String word = "cat";
        final String translation = "кот, кошка";
        System.setIn(new ByteArrayInputStream(translation.getBytes()));
        dispatcher.dispatch(InteractionEvents.UNKNOWN_WORD, word);
        final int wantedNumberOfInvocations = 1;
        verify(observer, times(wantedNumberOfInvocations)).onUnknownWord(word);
        final String expectedMessage = "Неизвестое слово \"" + word + "\". " +
            "Введите перевод или пустую строку для отказа.\n" +
            "Слово(а) \"" + translation + "\" добавлено(ы) в словарь.";
        assertEquals(
            expectedMessage.replace("\n", "").replace("\r", ""),
            mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    @Test
    public void testDispatchUnknownWordIgnore() {
        final var original = System.in;
        final String word = "cat";
        final String translation = "\n";
        System.setIn(new ByteArrayInputStream(translation.getBytes()));
        dispatcher.dispatch(InteractionEvents.UNKNOWN_WORD, word);
        final int wantedNumberOfInvocations = 1;
        verify(observer, times(wantedNumberOfInvocations)).onUnknownWord(word);
        final String expectedMessage = "Неизвестое слово \"" + word + "\". " +
            "Введите перевод или пустую строку для отказа.\n" +
            "Слово \"" + word + "\" проигорировано.";
        assertEquals(
            expectedMessage.replace("\n", "").replace("\r", ""),
            mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    @Test
    public void testDispatchExit() {
        AtomicBoolean called = new AtomicBoolean(false);
        final Runnable callback = () -> called.set(true);
        dispatcher.dispatch(InteractionEvents.EXIT, callback);
        final int wantedNumberOfInvocations = 1;
        verify(observer, times(wantedNumberOfInvocations)).onExit(callback);
        final String expectedMessage = "Изменения сохранены. До свидания.";
        assertEquals(expectedMessage, mock.toString().strip());
        assertTrue(called.get());
    }

    @Test
    public void testDispatchPrintWord() {
        final String word = "cat";
        dictionary.add(word, "кот");
        dictionary.add(word, "кошка");
        dispatcher.dispatch(InteractionEvents.PRINT_WORD, word);
        final int wantedNumberOfInvocations = 1;
        verify(observer, times(wantedNumberOfInvocations)).onPrintWord(word);
        final String expectedMessage = "[кот, кошка]";
        assertEquals(expectedMessage, mock.toString().strip());
    }
}
