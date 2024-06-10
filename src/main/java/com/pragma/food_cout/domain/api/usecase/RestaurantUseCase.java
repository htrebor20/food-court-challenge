package com.pragma.food_cout.domain.api.usecase;

import com.pragma.food_cout.configuration.client.IUserClient;
import com.pragma.food_cout.domain.Constants;
import com.pragma.food_cout.domain.api.IRestaurantServicePort;
import com.pragma.food_cout.domain.enums.RoleEnum;
import com.pragma.food_cout.domain.exception.BadRequestValidationException;
import com.pragma.food_cout.domain.model.Restaurant;
import com.pragma.food_cout.domain.model.User;
import com.pragma.food_cout.domain.spi.IRestaurantPersistencePort;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserClient iUserClient;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserClient iUserClient) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.iUserClient = iUserClient;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {

        validateRestaurant(restaurant);
        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public Restaurant findById(Long id) {
        Optional<Restaurant> restaurant = Optional.ofNullable(restaurantPersistencePort.findById(id));
        if (restaurant.isEmpty()) {
            throw new BadRequestValidationException(String.format(Constants.ID_VALIDATIONS_EXCEPTION_MESSAGE, id));
        }
        return restaurant.get();
    }

    private void validateRestaurant(Restaurant restaurant) {
        this.validateRestaurantName(restaurant.getName());
        this.validateRestaurantPhone(restaurant.getPhone());
        this.validateTaxId(restaurant.getTaxId());
        this.validateOwnerRole(restaurant.getOwnerId());
    }

    private void validateRestaurantName(String restaurantName) {
        String regex = "^.*\\D+.*$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(restaurantName).matches()) {
            throw new BadRequestValidationException(String.format(Constants.INVALID_FORMAT, restaurantName));
        }
    }

    private void validateRestaurantPhone(Long phone) {
        String phoneStr = phone.toString();
        String regex = "[+]?[0-9]{1,13}";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(phoneStr).matches()) {
            throw new BadRequestValidationException(String.format(Constants.INVALID_FORMAT, phone));
        }
    }

    private void validateTaxId(Long taxId) {
        String taxIdStr = taxId.toString();
        String regex = "[0-9]*";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(taxIdStr).matches()) {
            throw new BadRequestValidationException(String.format(Constants.INVALID_FORMAT, taxId));
        }
    }

    private void validateOwnerRole(Long ownerId) {
        try {
            ResponseEntity<User> userById = iUserClient.findUserById(ownerId);
            User user = userById.getBody();
            if (!Objects.requireNonNull(user).getRole().getName().equals(RoleEnum.OWNER.getRoleName())) {
                throw new BadRequestValidationException(String.format(Constants.ROL_VALIDATIONS_MESSAGE, ownerId));
            }
        } catch (Exception e) {
            throw new BadRequestValidationException(String.format(Constants.ID_VALIDATIONS_EXCEPTION_MESSAGE, ownerId));
        }
    }
}
