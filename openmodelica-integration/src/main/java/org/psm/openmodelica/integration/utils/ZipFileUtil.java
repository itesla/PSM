package org.psm.openmodelica.integration.utils;

/*
 * #%L
 * Dynamic simulation using OpenModelica
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Silvia Machado <machados at aia.es>
 */
public class ZipFileUtil
{

	public static void unzipFileIntoDirectory(ZipFile zipFile, File jiniHomeParentDir)
	{
		Enumeration files = zipFile.entries();
		File f = null;
		FileOutputStream fos = null;

		while (files.hasMoreElements())
		{
			try
			{
				ZipEntry entry = (ZipEntry) files.nextElement();
				InputStream eis = zipFile.getInputStream(entry);
				byte[] buffer = new byte[1024];
				int bytesRead = 0;

				f = new File(jiniHomeParentDir.getAbsolutePath() + File.separator + entry.getName());

				if (entry.isDirectory())
				{
					f.mkdirs();
					continue;
				}
				else
				{
					f.getParentFile().mkdirs();
					f.createNewFile();
				}

				fos = new FileOutputStream(f);

				while ((bytesRead = eis.read(buffer)) != -1)
				{
					fos.write(buffer, 0, bytesRead);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				continue;
			}
			finally
			{
				if (fos != null)
				{
					try
					{
						fos.close();

					}
					catch (IOException e)
					{
						// ignore
					}
				}
			}
		}
	}

}