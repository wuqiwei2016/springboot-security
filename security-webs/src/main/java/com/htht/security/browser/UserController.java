/**
 * 
 */
package com.htht.security.browser;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wqw
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/me")
	public Object getCurrentUser() {
		return "你好";
	}





}
