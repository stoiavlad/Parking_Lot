package org.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.common.CarDto;
import org.common.CarPhotoDto;
import org.example.parkinglot.entities.Car;
import org.example.parkinglot.entities.CarPhoto;
import org.example.parkinglot.entities.User;

import java.util.*;
import java.util.logging.Logger;

@Stateless
public class CarsBean {

    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;


    public void createCar(String licensePlate, String parkingSpot, Long userId) {
        LOG.info("createCar");

        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user = entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);

        entityManager.persist(car);
    }


    public CarDto findById(Long carId) {
        LOG.info("findById");

        Car car = entityManager.find(Car.class, carId);
        if (car == null) {
            return null;
        }

        List<Car> list = new ArrayList<>();
        list.add(car);

        return copyCarsToDto(list).get(0);
    }

    public List<CarDto> findAllCars() {
        LOG.info("findAllCars");
        try {
            TypedQuery<Car> typedQuery =
                    entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> carDtos = new ArrayList<>();
        for (Car car : cars) {
            carDtos.add(new CarDto(
                    car.getId(),
                    car.getLicensePlate(),
                    car.getParkingSpot(),
                    car.getOwner().getUsername(),
                    car.getOwner().getId()
            ));
        }
        return carDtos;
    }


    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long userId) {
        LOG.info("updateCar");

        Car car = entityManager.find(Car.class, carId);
        if (car == null) {
            throw new EJBException("Car not found: " + carId);
        }

        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user = entityManager.find(User.class, userId);
        if (user == null) {
            throw new EJBException("User not found: " + userId);
        }

        car.setOwner(user);
    }

    public void deleteCarsByIds(Collection<Long> carIds) {
        LOG.info("deleteCarsByIds");

        for (Long carId : carIds) {
            Car car = entityManager.find(Car.class, carId);
            entityManager.remove(car);
        }
    }

    public void addPhotoToCar(Long carId,
                              String filename,
                              String fileType,
                              byte[] fileContent) {

        LOG.info("addPhotoToCar");

        Car car = entityManager.find(Car.class, carId);
        if (car == null) {
            throw new EJBException("Car not found: " + carId);
        }

        CarPhoto photo = new CarPhoto();
        photo.setFilename(filename);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);

        if (car.getPhoto() != null) {
            entityManager.remove(car.getPhoto());
        }

        car.setPhoto(photo);
        photo.setCar(car);

        entityManager.persist(photo);
    }

    public CarPhotoDto findPhotoByCarId(Long carId) {

        List<CarPhoto> photos = entityManager.createQuery(
                        "SELECT p FROM CarPhoto p WHERE p.car.id = :id",
                        CarPhoto.class
                )
                .setParameter("id", carId)
                .getResultList();

        if (photos.isEmpty()) {
            return null;
        }

        CarPhoto photo = photos.get(0);

        return new CarPhotoDto(
                photo.getId(),
                photo.getFilename(),
                photo.getFileType(),
                photo.getFileContent()
        );
    }

    public void sortCar(Long carId) {
        LOG.info("sortCar");
        Car car = entityManager.find(Car.class, carId);
        Scanner sc=new Scanner(System.in);
        Scanner sc1=new Scanner(System.in);
        int n = 0;
        String str[]=new String[n];
        for(int i=0; i<n ;i++)
        {
            str[i]=sc1.nextLine();
        }
    Arrays.sort(str,String.CASE_INSENSITIVE_ORDER);
    }




}
