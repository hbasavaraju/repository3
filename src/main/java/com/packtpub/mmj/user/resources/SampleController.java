package com.packtpub.mmj.user.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.packtpub.mmj.user.domain.model.entity.User;
import com.packtpub.mmj.user.domain.service.UserService;

@RestController
public class SampleController {
	@Autowired
	protected UserService userService;

	@GetMapping("/v1/user/{name}")
	public ResponseEntity<Collection<User>> findByName(
			@RequestParam("name") String name) {
		// logger.info(String.format("user-service findByName() invoked:{} for {} ",
		// userService.getClass().getName(), name));
		name = name.trim().toLowerCase();
		Collection<User> users;
		try {
			users = userService.findByName(name);
		} catch (Exception ex) {
			// logger.log(Level.SEVERE, "Exception raised findByName REST Call",
			// ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return users.size() > 0 ? new ResponseEntity<>(users, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
