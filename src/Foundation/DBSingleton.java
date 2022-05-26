package Foundation;

public class DBSingleton {

    private static DB instance;

    // Private so you cannot "new" it
    private DBSingleton()
    {

    }

    /**
     * Gets the instance, it is synchronized because in theory it can be used by multiple threads
     * @return the instance
     */
    public synchronized static DB getInstance()
    {
        // Create the instance if it hasn't been initiated
        if (instance==null)
        {
            instance = new DB();
        }
        // Return the instance
        return instance;
    }
}
