import java.io.*;
import java.util.*;

public class FIle_Rename_Recursive {
  private static String SOURCE_BASE_FOLDER = "D:\\gradle-2.3";
  private static List<String> sourceFileList = new ArrayList<String>();
  /******************************************************************************************/
  public static void main(String[] args) throws Exception {
	if(args != null || args.length > 0) {
		SOURCE_BASE_FOLDER = args[0];
	}
    System.out.println("SOURCE_BASE_FOLDER ="+SOURCE_BASE_FOLDER);
	File sourceFolder = new File(SOURCE_BASE_FOLDER);
	if(!sourceFolder.exists()) {
		System.out.println("Folder "+SOURCE_BASE_FOLDER + " does not exist. Exiting");
		System.exit(-1);
	}
    sourceFileList = FileUtil.getFileListFromFolder(SOURCE_BASE_FOLDER);
    for(int i=0;i<sourceFileList.size();i++) {
    	String fileName = sourceFileList.get(i);
    	if(fileName.endsWith(".md5")) {
    		continue;
    	}
    	File md5File = new File(fileName+".md5");
    	if(md5File.exists()) {
    		continue;
    	}
        System.out.println("fileName="+fileName);
        String md5String = MD5Util.getMD5ChecksumAsHEX(fileName);
        FileOutputStream fos =  new FileOutputStream(md5File);
        fos.write((md5String + " *" + md5File.getName().substring(0,md5File.getName().length()-4)).getBytes());
        fos.close();
    }
  }

}
