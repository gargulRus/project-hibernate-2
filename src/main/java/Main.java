import config.MySessionFactory;
import dao.*;
import entitys.*;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private final Session session;

    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    public Main() {
        session = MySessionFactory.getSessionFactory().openSession();
        actorDAO = new ActorDAO(session);
        addressDAO = new AddressDAO(session);
        categoryDAO = new CategoryDAO(session);
        cityDAO = new CityDAO(session);
        countryDAO = new CountryDAO(session);
        customerDAO = new CustomerDAO(session);
        filmDAO = new FilmDAO(session);
        filmTextDAO = new FilmTextDAO(session);
        inventoryDAO = new InventoryDAO(session);
        languageDAO = new LanguageDAO(session);
        paymentDAO = new PaymentDAO(session);
        rentalDAO = new RentalDAO(session);
        staffDAO = new StaffDAO(session);
        storeDAO = new StoreDAO(session);
    }

    public static void main(String[] args) {
        Main main = new Main();

        //Пункт задания 6  - создание Клиента
        Customer customer = main.createCustomer();
        //Пункт задания 7  - возвращение клиентом арендованного фильма
        main.customerReturnInventoryToStore();
        //Пункт задания 8  - Оплата аренды фильма клиентом
        main.customerRentInventory(customer);
        //Пункт задания 9  - Создание фильма
        main.newFilmWasMade();

        main.session.close();
    }

    private void newFilmWasMade() {
        try {
            session.beginTransaction();

            Language language = languageDAO.getItems(0, 20).stream().unordered().findAny().get();

            List<Category> categories = categoryDAO.getItems(0, 5);
            List<Actor> actors = actorDAO.getItems(0, 20);

            Film film = new Film();
            film.setActors(new HashSet<>(actors));
            film.setRating(Rating.NC17);
            film.setSpecialFeatures(Set.of(Feature.TRAILERS, Feature.COMMENTARIES));
            film.setLength((short) 44);
            film.setReplacementCost(BigDecimal.TEN);
            film.setRentalRate(BigDecimal.ZERO);
            film.setLanguage(language);
            film.setDescription("new film");
            film.setTitle("NEW FILM");
            film.setRentalDuration((byte) 44);
            film.setOriginalLanguage(language);
            film.setCategories(new HashSet<>(categories));
            film.setReleaseYear(Year.now());
            filmDAO.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilm(film);
            filmText.setDescription("new film");
            filmText.setTitle("NEW FILM");
            filmText.setFilmId(film.getFilmId());
            filmTextDAO.save(filmText);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void customerRentInventory(Customer customer) {
        try {
            session.beginTransaction();

            Film film = filmDAO.getFirstAvialabaleFilmToRent();
            Store store = storeDAO.getItems(0, 1).get(0);

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryDAO.save(inventory);

            Staff staff = store.getStaff();

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);

            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setRental(rental);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(66.77));
            payment.setStaff(staff);
            paymentDAO.save(payment);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void customerReturnInventoryToStore() {
        try {
            session.beginTransaction();

            Rental rental = rentalDAO.getAnyUnreturnedRental();

            rental.setReturnDate(LocalDateTime.now());

            rentalDAO.save(rental);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Customer createCustomer() {
        try {
            session.beginTransaction();

            Store store = storeDAO.getItems(0, 1).get(0);

            City city = cityDAO.getByName("Jhansi");

            Address address = new Address();
            address.setAddress("address1");
            address.setPhone("999-11-55");
            address.setCity(city);
            address.setDistrict("District1");
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setIsActive(true);
            customer.setEmail("123@123.ru");
            customer.setAddress(address);
            customer.setStore(store);
            customer.setFirstName("FirstName");
            customer.setLastName("LastName");
            customerDAO.save(customer);

            session.getTransaction().commit();
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
