import dal.IUserDAO.DALException;

public class Run {
	static WeightingSystem ws = new WeightingSystem();
	public static void main(String[] args) {
		try {
			ws.Run();
		} catch (DALException e) {
			System.out.println("Åhh naj");
		} finally {
			ws.Close();
		}
		
	}
}
