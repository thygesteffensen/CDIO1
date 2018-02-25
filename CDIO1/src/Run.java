import dal.IUserDAO.DALException;

public class Run {
	static WeightingSystem ws = new WeightingSystem();
	public static void main(String[] args) {
		try {
			ws.Run();
		} catch (DALException e) {
			System.err.println("Some mistake happend, while trying to run the WeightSystem.\n");
			e.printStackTrace();
		} finally {
//			ws.Close();
		}
		
	}
}
