package action;
import com.dao.UserDao;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import model.Arctle;

public class DoArctle extends ActionSupport implements ModelDriven<Arctle> {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Arctle arctle =new Arctle();
	public DoArctle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Arctle getModel() {
		// TODO Auto-generated method stub
		return arctle;
	}

    public String del_arctle() {
    	if(UserDao.delArctle(this.getModel().getId()))
    	  return "seccuss";
		return "fail";
  
    }
    public String add_arctle() {
        System.out.println(this.getModel().getAuthor_name());
    	
    	if (UserDao.addArctle(this.getModel()))
    	return "seccuss";
     return "fail";
    	
    }
    
	public String  update_arctle() {
	   if (UserDao.UpdateArctle(this.getModel()))
		return "success";
	   else 
		   return "fail";
		
	}
	

}
