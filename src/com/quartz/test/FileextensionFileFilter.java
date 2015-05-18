
package com.quartz.test;

import java.io.File;
import java.io.FileFilter;

/**
 * <b>  </b>
 * <p>
 *     功能:
 * </p>
 * @作者  张涛
 * @创建日期 2013-12-24
 * @项目名称 quartzDemo
 * @JAVA路径 com.quartz.test.FileextensionFileFilter
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
