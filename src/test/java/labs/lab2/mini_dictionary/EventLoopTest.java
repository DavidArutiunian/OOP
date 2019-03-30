package labs.lab2.mini_dictionary;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class EventLoopTest {
    private final PrintStream original = System.out;
    private Dictionary dictionary = new Dictionary();
    private final DictionaryStore store = new DictionaryStore();
    private final InteractionController controller = spy(new InteractionController(dictionary, store));
    private ByteArrayOutputStream mock = new ByteArrayOutputStream();

    public EventLoopTest() throws IOException {
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
        dictionary = new Dictionary();
    }

    @Test
    public void testStateEmptyInput() {
        controller.onEmptyInput();
        final int wantedNumberOfInvocations = 1;
        verify(controller, times(wantedNumberOfInvocations)).onEmptyInput();
        final String expectedMessage = "Пусто. Введите заново.";
        assertEquals(expectedMessage, mock.toString().strip());
    }

    @Test
    public void testStateFinish() throws IOException {
        final var original = System.in;
        System.setIn(new ByteArrayInputStream("Y".getBytes()));
        controller.onFinishWord();
        final int wantedNumberOfInvocations = 1;
        verify(controller, times(wantedNumberOfInvocations)).onFinishWord();
        controller.onExit();
        verify(controller, times(wantedNumberOfInvocations)).onExit();
        final String expectedMessage = "В словарь были внесены изменения. Введите Y или y для сохранения перед выходом.\n" +
            "Изменения сохранены. До свидания.";
        assertEquals(
            expectedMessage.replace("\n", "").replace("\r", ""),
            mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    @Test
    public void testStateUnknownWordSave() {
        final var original = System.in;
        final String word = "cat";
        final String translation = "кот, кошка";
        System.setIn(new ByteArrayInputStream(translation.getBytes()));
        controller.onInputWord(word);
        final int wantedNumberOfInvocations = 1;
        verify(controller, times(wantedNumberOfInvocations)).onInputWord(word);
        final String expectedMessage = "Неизвестное слово \"" + word + "\". " +
            "Введите перевод или пустую строку для отказа.\n" +
            "Слово(а) \"" + translation + "\" добавлено(ы) в словарь.";
        assertEquals(
            expectedMessage.replace("\n", "").replace("\r", ""),
            mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    @Test
    public void testStateEmptyWord() {
        final var original = System.in;
        final String word = "\n";
        System.setIn(new ByteArrayInputStream(word.getBytes()));
        controller.onEmptyInput();
        final String expectedMessage = "Пусто. Введите заново.";
        assertEquals(
            expectedMessage.replace("\n", "").replace("\r", ""),
            mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    @Test
    public void testStateUnknownWordIgnore() {
        final var original = System.in;
        final String word = "cat";
        final String translation = "\n";
        System.setIn(new ByteArrayInputStream(translation.getBytes()));
        controller.onInputWord(word);
        final int wantedNumberOfInvocations = 1;
        verify(controller, times(wantedNumberOfInvocations)).onInputWord(word);
        final String expectedMessage = "Неизвестное слово \"" + word + "\". " +
            "Введите перевод или пустую строку для отказа.\n" +
            "Слово \"" + word + "\" проигнорировано.";
        assertEquals(
            expectedMessage.replace("\n", "").replace("\r", ""),
            mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    @Test
    public void testStateExit() throws IOException {
        final var original = System.in;
        System.setIn(new ByteArrayInputStream("Y".getBytes()));
        controller.onFinishWord();
        final int wantedNumberOfInvocations = 1;
        verify(controller, times(wantedNumberOfInvocations)).onFinishWord();
        controller.onExit();
        verify(controller, times(wantedNumberOfInvocations)).onExit();
        final String expectedMessage = "В словарь были внесены изменения. Введите Y или y для сохранения перед выходом.\n" +
            "Изменения сохранены. До свидания.";
        assertEquals(
            expectedMessage.replace("\n", "").replace("\r", ""),
            mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    @Test
    public void testDispatchPrintWord() {
        final String word = "cat";
        dictionary.add(word, "кот");
        dictionary.add(word, "кошка");
        controller.onInputWord(word);
        final int wantedNumberOfInvocations = 1;
        verify(controller, times(wantedNumberOfInvocations)).onInputWord(word);
        final String expectedMessage = "[кот, кошка]";
        assertEquals(expectedMessage, mock.toString().strip());
    }
}
