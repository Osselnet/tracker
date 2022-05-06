package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowByIdActionTest {

    @Test
    public void whenFindSuccess() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = new Item("New item");
        tracker.add(item);
        ShowByIdAction find = new ShowByIdAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ===" + ln + item + ln));
        assertThat(tracker.findAll().get(0), is(item));
    }

    @Test
    public void whenFindFail() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = new Item("New item");
        tracker.add(item);
        ShowByIdAction find = new ShowByIdAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(2);

        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ===" + ln
                + "Заявка с введенным id: " + 2 + " не найдена." + ln));
    }
}