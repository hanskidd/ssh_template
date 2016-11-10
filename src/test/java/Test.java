


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.domain.User;
import com.service.UserService;

@Transactional
public class Test extends SpringJunitTest{

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;
	@org.junit.Test
	@Rollback(value = false)
	public void test(){
		User user = new User(1, "张三", 1, "2015-01-01");
		userService.save(user);
	}
	
	
}
