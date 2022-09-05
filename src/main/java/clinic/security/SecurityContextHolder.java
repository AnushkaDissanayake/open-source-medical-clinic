package clinic.security;

public class SecurityContextHolder {

    private static User user;

    public static void setPrinciple(User user){
        if (user==null){
            throw new NullPointerException("Principal can't be null");
        } else if (user.getUsername()==null || user.getUsername().isBlank()||user.getRole()==null ) {
            throw new RuntimeException("Invalid user");

        }
        SecurityContextHolder.user =user;
        System.out.println(user);

    }
    public static User getPrinciple(){
        if (user!=null){

        }
        return user;
    }
    public void clear(){
        user=null;
    }
}
