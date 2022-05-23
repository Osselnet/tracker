package ru.job4j.tracker.store;

import org.junit.Test;
import ru.job4j.tracker.Item;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HbmTrackerTest {
    @Test
    public void whenAddSuccess() {
        Store tracker = new HbmTracker();
        Item expect = new Item("name1", "description1");
        tracker.add(expect);

        List<Item> items = tracker.findAll();
        Item result = items.get(0);

        assertThat(items.size(), is(1));
        assertThat(result.getId(), is(expect.getId()));
        assertThat(result.getName(), is(expect.getName()));
        assertThat(result.getDescription(), is(expect.getDescription()));
        assertThat(result.getCreated().withNano(0), is(expect.getCreated().withNano(0)));
    }

    @Test
    public void whenReplaceSuccess() {
        Store tracker = new HbmTracker();
        Item item = tracker.add(new Item("name1", "description1"));
        int id = item.getId();
        Item expect = new Item("name2", "description2");
        tracker.replace(Integer.toString(id), expect);

        List<Item> items = tracker.findAll();
        Item result = items.get(0);

        assertThat(items.size(), is(1));
        assertThat(result.getId(), is(expect.getId()));
        assertThat(result.getName(), is(expect.getName()));
        assertThat(result.getDescription(), is(expect.getDescription()));
        assertThat(result.getCreated().withNano(0), is(expect.getCreated().withNano(0)));
    }

    @Test
    public void whenDeleteSuccess() {
        Store tracker = new HbmTracker();
        Item item = tracker.add(new Item("name1", "description1"));
        int id = item.getId();
        tracker.delete(Integer.toString(id));

        List<Item> items = tracker.findAll();

        assertThat(items.size(), is(0));
    }

    @Test
    public void whenFindAll() {
        Store tracker = new HbmTracker();
        Item expect1 = tracker.add(new Item("name1", "description1"));
        Item expect2 = tracker.add(new Item("name2", "description2"));

        List<Item> items = tracker.findAll();
        Item result1 = items.get(0);
        Item result2 = items.get(1);

        assertThat(items.size(), is(2));
        assertThat(result1.getId(), is(expect1.getId()));
        assertThat(result1.getName(), is(expect1.getName()));
        assertThat(result1.getDescription(), is(expect1.getDescription()));
        assertThat(result1.getCreated().withNano(0), is(expect1.getCreated().withNano(0)));

        assertThat(result2.getId(), is(expect2.getId()));
        assertThat(result2.getName(), is(expect2.getName()));
        assertThat(result2.getDescription(), is(expect2.getDescription()));
        assertThat(result2.getCreated().withNano(0), is(expect2.getCreated().withNano(0)));
    }

    @Test
    public void whenFindByIdSuccess() {
        Store tracker = new HbmTracker();
        Item expect = tracker.add(new Item("name1", "description1"));
        int id = expect.getId();
        Item result = tracker.findById(Integer.toString(id));

        assertThat(result.getId(), is(expect.getId()));
        assertThat(result.getName(), is(expect.getName()));
        assertThat(result.getDescription(), is(expect.getDescription()));
        assertThat(result.getCreated().withNano(0), is(expect.getCreated().withNano(0)));
    }

    @Test
    public void whenFindByNameSuccess() {
        Store tracker = new HbmTracker();
        Item expect = tracker.add(new Item("name1", "description1"));
        String name = expect.getName();
        List<Item> items = tracker.findByName(name);
        Item result = items.get(0);

        assertThat(items.size(), is(1));
        assertThat(result.getId(), is(expect.getId()));
        assertThat(result.getName(), is(expect.getName()));
        assertThat(result.getDescription(), is(expect.getDescription()));
        assertThat(result.getCreated().withNano(0), is(expect.getCreated().withNano(0)));
    }

    @Test
    public void whenReplaceFail() {
        Store tracker = new HbmTracker();
        tracker.add(new Item("name1", "description1"));
        int id = 2;
        Item newItem = new Item("name2", "description2");
        boolean result = tracker.replace(Integer.toString(id), newItem);

        assertThat(result, is(false));
    }

    @Test
    public void whenDeleteFail() {
        Store tracker = new HbmTracker();
        tracker.add(new Item("name1", "description1"));
        int id = 2;
        boolean result = tracker.delete(Integer.toString(id));

        assertThat(result, is(false));
    }

    @Test
    public void whenFindByIdFail() {
        Store tracker = new HbmTracker();
        tracker.add(new Item("name1", "description1"));
        int id = 2;
        Item result = tracker.findById(Integer.toString(id));

        assertNull(result);
    }

    @Test
    public void whenFindByNameFail() {
        Store tracker = new HbmTracker();
        tracker.add(new Item("name1", "description1"));
        String name = "name2";
        List<Item> result = tracker.findByName(name);
        System.out.println(result);

        assertThat(result.size(), is(0));
    }
}