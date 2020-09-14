
public class DataSetGen<T>extends DataSet<T>{

	private T t;
	
	public void set(T t) {
		
		this.t = t;
	}
	
	public T get() {
		
		return t;
	}
}
