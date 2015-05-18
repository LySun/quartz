
package com.quartz.test;

import java.io.File;
import java.io.FileFilter;

/**
 * <b>  </b>
 * <p>
 *     ����:
 * </p>
 * @����  ����
 * @�������� 2013-12-24
 * @��Ŀ���� quartzDemo
 * @JAVA·�� com.quartz.test.FileextensionFileFilter
 */
public class FileextensionFileFilter implements FileFilter {
    private String extension;
    
	public FileextensionFileFilter(String extension) {
		super();
		this.extension = extension;
	}

	@Override
	public boolean accept(File arg0) {
		String icasename=arg0.getName().toLowerCase();
		return (arg0.isFile()&&(icasename.indexOf(extension)>0))?true:false;
	}

}
