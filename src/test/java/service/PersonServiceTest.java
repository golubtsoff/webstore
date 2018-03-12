package service;

import static org.junit.Assert.*;

import entity.Item;
import entity.Person;
import entity.Role;
import exception.DBException;
import org.junit.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonServiceTest {

    private final PersonService personService = new PersonServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSignIn() throws Exception {
        String login = "Boris";
        String password = "pass123";
        Person person = new Person(login, password);

        personService.signUp(login, password);
        Person testPerson = personService.signIn(login, password);
        assertEquals("Not autorized", person, testPerson);
    }

    @Test(expected = DBException.class)
    public void testSignInException() throws Exception {
        String login = "Jack";
        String password = "pass123";
        personService.signIn(login, password);
    }

    @Test
    public void testSignUp() throws Exception {
        String login = "Alex";
        String password = "pass123";
        Person person = new Person(login, password);

        Person testPerson = personService.signUp(login, password);
        assertEquals("Not registerd", person, testPerson);
    }

    @Test(expected = DBException.class)
    public void testSignUpException() throws Exception {
        String login = "Michael";
        String password = "pass123";
        personService.signUp(login, password);
        personService.signUp(login, password);
    }

    @Test
    public void testGetItem() throws Exception {
        Item item = new Item(
                "Настольная игра Имаджинариум Юбилейное издание ",
                "«Имаджинариум. 5 лет» — настольная игра, в которой вам предстоит придумывать ассоциации к интереснейшим картинкам, выпущенная к юбилею популярной серии. Издание с новыми волшебными иллюстрациями, яркими стеклянными фигурками игрока (индивидуальные, неповторяющиеся) и удобнейшим органайзером для хранения элементов и дополнений!",
                new BigDecimal(2250),
                2);
        Long itemId = adminService.createItem(item);
        Item testItem = personService.getItem(itemId);
        assertEquals("Wrong item", item, testItem);
    }

    @Test
    public void testGetItemIsNull() throws Exception {
        Item item = adminService.getItem(-1L);
        assertNull(item);
    }

    @Test
    public void testGetItems() throws Exception {
        List<Item> items = new ArrayList<>();
        Map<Long, Item> mapItems = new HashMap<>();
        items.add(new Item("Конструктор Самоделкин цветной 80 моделей",
                "Конструктор \"Самоделкин цветной 80 моделей\"- настоящая классика среди игрушек подобного рода. Такие же конструкторы были популярны в советское время и использовались на уроках труда в младших классах. А у сегодняшних родителей есть шанс подарить ребенку игрушку из своего детства. В состав набора входит 307 деталей. Большинство основных деталей окрашено, что делает готовые модели более яркими.",
                new BigDecimal(865),
                10));
        items.add(new Item("Планшет обучающий мини",
                "Интерактивный обучающий планшет поможет подготовить малыша к школьной программе и освоить ему русский алфавит, пополнить свой словарный запас новыми словами, изучить цифры и основы счёта, а также музыкальные ноты. Игрушка, выполненная в виде современного гаджета, работает в 7 программных режимах. Малыш сможет сам проверить, насколько хорошо он усвоил материал в режиме «тест» или «экзамен». На планшете предусмотрена кнопка регулировки громкости. Интерактивная игра поможет ребенку развить память, внимательность, восприятие и усидчивость. Простой, яркий и забавный интерфейс не позволит заскучать.",
                new BigDecimal(470),
                0));
        items.add(new Item("Фокусы с наперстками и маркировкой",
                "Настольная игра с наперстками и маркировкой – подарит детям возможность научиться выполнять различные трюки. Дети с удовольствием будут показывать захватывающие фокусы своим друзьям и близким. С этим набором дети смогут научиться выполнять 10 фокусов с наперстками, а если собрать всю серию, то фокусы можно будет комбинировать. Благодаря подробной цветной инструкции дети быстро и легко выучат все трюки.",
                new BigDecimal(531),
                1));
        for (Item item : items) {
            mapItems.put(adminService.createItem(item), item);
        }

        String login = "Sid";
        String password = "pass123";
        Person userPerson = new Person(login, password);
        List<Item> testItems = personService.getItems(userPerson);

        assertEquals("Item's lists not equals", testItems.size(), mapItems.size()-1);
        for(Item item : testItems){
            assertEquals("Item and test item not equals", item, mapItems.get(item.getId()));
        }

        Person adminPerson = new Person(login, password, Role.admin);
        testItems = personService.getItems(adminPerson);

        assertEquals("Item's lists not equals", testItems.size(), mapItems.size());
        for(Item item : testItems){
            assertEquals("Item and test item not equals", item, mapItems.get(item.getId()));
        }
    }

}