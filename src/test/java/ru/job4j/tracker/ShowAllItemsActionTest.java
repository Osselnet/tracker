package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowAllItemsActionTest {

    @Test
    public void whenFindSuccess() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item1 = new Item("New item 1");
        Item item2 = new Item("New item 2");
        Item item3 = new Item("New item 3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        ShowAllItemsAction find = new ShowAllItemsAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Show all items ===" + ln + item1 + ln + item2 + ln + item3 + ln));
        assertThat(tracker.findAll().get(0), is(item1));
        assertThat(tracker.findAll().get(1), is(item2));
        assertThat(tracker.findAll().get(2), is(item3));
    }

    @Test
    public void whenFindFail() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        ShowAllItemsAction find = new ShowAllItemsAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(2);

        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Show all items ===" + ln
                + "Хранилище еще не содержит заявок." + ln));
    }
}