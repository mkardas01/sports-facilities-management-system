package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.dto.User.UserDTO;
import put.poznan.sport.entity.User;
import put.poznan.sport.service.user.UserService;

@RestController
@RequestMapping("api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("all")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

   @GetMapping
   @CrossOrigin
   @ResponseBody
   public ResponseEntity<?> getUser() {
        return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
   }

   @PostMapping("create")
   @CrossOrigin
   @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
   }

   @PutMapping("update")
   @CrossOrigin
   @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
   }
    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
    }
}
