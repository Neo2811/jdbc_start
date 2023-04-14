package am.hitech.jdbc.service;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.repo.UserRepo;
import am.hitech.jdbc.util.exceptionMassage.ErrorMassage;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

public class UserService {
    UserRepo userRepo = new UserRepo();

    public User getByUser(String userName) throws NotFoundException {

        if (userRepo.getByUser(userName) == null) {
            throw new NotFoundException(ErrorMassage.NOT_FOUND_USER);
        }
        return userRepo.getByUser(userName);
    }

    public User getById(int id) throws NotFoundException, InternalServerError {
        try {
            User user = userRepo.getById(id);
            if (user == null) {
                throw new NotFoundException(ErrorMassage.ANOTHER_ERROR_MASSAGE);
            }
            return user;
        } catch (RuntimeException e) {
            throw new InternalServerError(ErrorMassage.ANOTHER_ERROR_MASSAGE);
        }
    }
}
