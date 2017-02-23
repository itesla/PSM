package org.power_systems_modelica.psm.modelica.engine.logs;

import java.util.ArrayList;
import java.util.List;

public class Logs
{
	public Logs()
	{
		currentCall = new CallLog("");
		save = true;
	}

	public void setSave(boolean save)
	{
		this.save = save;
	}

	public void newCall(String call)
	{
		if (!save) return;
		currentCall = new CallLog(call);
		calls.add(currentCall);
	}

	public void status(String message)
	{
		if (!save) return;
		currentCall.status(message);
	}

	public void reply(String reply)
	{
		if (!save) return;
		currentCall.reply(reply);
	}

	public void callError(String error)
	{
		if (!save) return;
		currentCall.callError(error);
	}
	
	public List<CallLog> getCalls()
	{
		return calls;
	}

	public void dump()
	{
		dump(0, "Logs saved during the dialog with Open Modelica");
		calls.forEach(c -> {
			dump(0, c.call);
			dump(1, "Statuses");
			for (int k = 0; k < c.statuses.size(); k++)
				dump(2, c.statuses.get(k), k);
			dump(1, "Replies");
			for (int k = 0; k < c.replies.size(); k++)
				dump(2, c.replies.get(k), k);
			if (c.callError != null)
			{
				dump(1, "Call error");
				dump(2, c.callError);
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
			System.out.printf("OMCLog %s%s%s%n",
					sindent,
					sindex,
					line);
		}
	}

	public String getLogsDump(StringBuilder sb)
	{
		calls.forEach(c -> {
			getLogsDump(sb, 0, c.call);
			getLogsDump(sb, 1, "Statuses");
			for (int k = 0; k < c.statuses.size(); k++)
				getLogsDump(sb, 2, c.statuses.get(k), k);
			getLogsDump(sb, 1, "Replies");
			for (int k = 0; k < c.replies.size(); k++)
				getLogsDump(sb, 2, c.replies.get(k), k);
			if (c.callError != null)
			{
				getLogsDump(sb, 1, "Call error");
				getLogsDump(sb, 2, c.callError);
			}
		});

		return sb.toString();
	}

	void getLogsDump(StringBuilder sb, int indent, String text)
	{
		getLogsDump(sb, indent, text, -1);
	}

	void getLogsDump(StringBuilder sb, int indent, String text, int index)
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

	List<CallLog>	calls	= new ArrayList<>();
	CallLog			currentCall;
	private boolean	save;
}
