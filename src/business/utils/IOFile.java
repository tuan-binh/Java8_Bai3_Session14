package business.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile<T>
{
	
	
	public List<T> readFromFile(String path)
	{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		// khởi tạo arrayList
		List<T> list = new ArrayList<>();
		try
		{
			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);
			list = (List<T>) ois.readObject();
//			return (List<T>) ois.readObject();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			try
			{
				if (fis != null)
				{
					fis.close();
				}
				if (ois != null)
				{
					ois.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// write - ghi To - đến File - tệp tin
	public void writeToFile(List<T> list, String path)
	{
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path)))
		{
			oos.writeObject(list);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
