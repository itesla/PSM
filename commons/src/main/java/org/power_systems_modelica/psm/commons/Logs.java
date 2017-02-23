package org.power_systems_modelica.psm.commons;

import java.util.ArrayList;
import java.util.List;

public class Logs
{
	public Logs(String title)
	{
		this.title = title;
		currentActivity = new LogActivity("");
		save = true;
	}

	public void setSave(boolean save)
	{
		this.save = save;
	}

	public void newActivity(String activity)
	{
		if (!save) return;
		currentActivity = new LogActivity(activity);
		activities.add(currentActivity);
	}

	public void detail(String message)
	{
		if (!save) return;
		currentActivity.detail(message);
	}

	public void result(String result)
	{
		if (!save) return;
		currentActivity.result(result);
	}

	public void activityError(String error)
	{
		if (!save) return;
		currentActivity.activityError(error);
	}

	public List<LogActivity> getActivities()
	{
		return activities;
	}

	public void dump()
	{
		dump(0, title);
		activities.forEach(a -> {
			dump(0, a.activity);
			if (a.details.size() > 0)
			{
				dump(1, "Details");
				for (int k = 0; k < a.details.size(); k++)
					dump(2, a.details.get(k), k);
			}
			if (a.results.size() > 0)
			{
				dump(1, "Results");
				for (int k = 0; k < a.results.size(); k++)
					dump(2, a.results.get(k), k);
			}
			if (a.callError != null)
			{
				dump(1, "Activity error");
				dump(2, a.callError);
			}
		});
	}

	private static final String[] INDENTS = { "", "    ", "        " };

	void dump(int indent, String text)
	{
		dump(indent, text, -1);
	}

	void dump(int indent, String text, int index)
	{
		String sindent = INDENTS[indent];
		String sindex = "";
		if (index >= 0) sindex = String.format("%-3d", index);
		String[] lines = text.split("\n");
		for (int k = 0; k < lines.length; k++)
		{
			String line = lines[k];
			System.out.printf("%s%s%s%n",
					sindent,
					sindex,
					line);
		}
	}

	public String dump(StringBuilder sb)
	{
		activities.forEach(c -> {
			dump(sb, 0, c.activity);
			if (c.details.size() > 0)
			{
				dump(sb, 1, "Details");
				for (int k = 0; k < c.details.size(); k++)
					dump(sb, 2, c.details.get(k), k);
			}
			if (c.results.size() > 0)
			{
				dump(sb, 1, "Results");
				for (int k = 0; k < c.results.size(); k++)
					dump(sb, 2, c.results.get(k), k);
			}
			if (c.callError != null)
			{
				dump(sb, 1, "Activity error");
				dump(sb, 2, c.callError);
			}
		});

		return sb.toString();
	}

	void dump(StringBuilder sb, int indent, String text)
	{
		dump(sb, indent, text, -1);
	}

	void dump(StringBuilder sb, int indent, String text, int index)
	{
		String sindent = INDENTS[indent];
		String sindex = "";
		if (index >= 0) sindex = String.format("%-3d", index);
		String[] lines = text.split("\n");
		for (int k = 0; k < lines.length; k++)
		{
			String line = lines[k];
			sb.append(sindent);
			sb.append(sindex);
			sb.append(line);
			sb.append("\n");
		}
	}

	private final String	title;
	List<LogActivity>		activities	= new ArrayList<>();
	LogActivity				currentActivity;
	private boolean			save;
}
