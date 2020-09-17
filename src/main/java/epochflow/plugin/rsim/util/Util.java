package epochflow.plugin.rsim.util;

import java.util.Random;

import epochflow.plugin.rsim.Configs;

public class Util {
	
	public static float Random(float min, float max)
	{
		return min + new Random().nextFloat() * max;
	}
	
	public static double Random(double min, double max)
	{
		return min + new Random().nextDouble() * max;
	}
	
	public static int Random(int min, int max)
	{
		return min + new Random().nextInt(max);
	}
	
	public static String getStringGauge(float value, String full, String empty)
	{
		StringBuffer buffer = new StringBuffer();
		int count = Math.round(value * 10);
		for	(int i = 0; i < count; i++)
			buffer.append(full);
		for	(int i = count; i < 10; i++)
			buffer.append(empty);
		return buffer.toString();
	}
	
	public static String getLangString(String path, String... value)
	{
		String result = Configs.getInstance().getLangConfig().getString(path);
		for(int i = 0; i < value.length; i++)
			result = result.replace("@" + i, value[0]);
		return result;
	}
}
