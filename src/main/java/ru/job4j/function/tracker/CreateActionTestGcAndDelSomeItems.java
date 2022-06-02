package ru.job4j.function.tracker;

public class CreateActionTestGcAndDelSomeItems implements UserAction {

    private final Output output;

    public CreateActionTestGcAndDelSomeItems(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Create many Items and delete some Items (for test GC)";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        output.printLn("=== Create many Item and delete some Items (for test GC) ===");
       // String name = input.askStr("Enter name");
       for (int i = 1; i < 100; i++) {
           Item item = new Item("name" + i);
           tracker.add(item);
           output.printLn("Добавленная заявка: " + item);
       }

       for (int i = 20; i < 80; i++) {
           tracker.delete(i);
           output.printLn("Заявка id: " + i + " удалена");
       }
        return true;
    }
}
