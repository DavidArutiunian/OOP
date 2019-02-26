package labs.lab2.mini_dictionary;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try {
            final Scanner scanner = new Scanner(System.in);
            final Dictionary dictionary = new DictionaryImpl();
            FileDictionaryProvider provider;
            if (args.length != 0) {
                provider = new FileDictionaryProviderImpl(dictionary, args[0]);
            } else {
                provider = new FileDictionaryProviderImpl(dictionary);
            }
            provider.load();
            final InteractionObserver observer = new InteractionObserverImpl(provider);
            final Dispatcher dispatcher = new DispatcherImpl(observer);
            final EventLoop loop = new EventLoopImpl(scanner, dispatcher);
            loop.run(dictionary);
        } catch (Exception e) {
            if (e.getMessage() == null) {
                System.err.println("Программная ошибка!");
            } else {
                System.err.println(e.getMessage());
            }
        }
    }
}
