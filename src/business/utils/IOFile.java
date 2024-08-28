package business.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile
{
	public static String PATH_DEPARTMENT = "./src/business/data/department.txt";
	
	public static <T> void writeObjectToFile(List<T> list, String path)
	{
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try
		{
			File file = new File(path);
			if (!file.exists())
			{
				file.createNewFile();
			}
			
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (fos != null)
				{
					fos.close();
				}
				if (oos != null)
				{
					oos.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static <T> List<T> readObjectFromFile(String path)
	{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		List<T> list = new ArrayList<>();
		try
		{
			File file = new File(path);
			if (!file.exists())
			{
				file.createNewFile();
			}
			
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			
			list = (List<T>) ois.readObject();
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
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
}
