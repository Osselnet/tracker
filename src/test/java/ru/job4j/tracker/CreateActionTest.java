package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateActionTest {
    @Test
    public void whenCreateSuccess() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        CreateAction create = new CreateAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("New item");

        create.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Create a new Item ===" + ln
                + "Добавленная заявка: " + tracker.findById(1) + ln));
        assertThat(tracker.findAll().size(), is(1));
    }
}
