package kun.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * 使用ListActivity来显示SD卡的文件
 * 用户可在此选择要显示的图片
 */
public class FileListActivity extends ListActivity {
	
	private File rootFile = null;
	private File currentFile = null;
	private ArrayList<HashMap<String, String>> fileList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//获取SD卡的路径
		rootFile = Environment.getExternalStorageDirectory();
		currentFile = rootFile;
		//显示文件列表
		showDir(rootFile);
	}
	
	//根据传入的File参数显示该File所在的文件
	public void showDir(File pathFile) {
		//ArrayList保存文件目录下每个文件条目，HashMap的键保存文件名，值保存文件路径
		fileList = new ArrayList<HashMap<String, String>>();
		if (pathFile != null && pathFile.exists()) {
			File[] files = pathFile.listFiles();
			//将父目录作为文件条目，用户可选择返回上层目录
			if (pathFile.getParentFile() != rootFile.getParentFile()) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("name", pathFile.getParentFile().getName());
				map.put("path", pathFile.getParent());
				fileList.add(map);
			}
			//遍历当前目录下的文件和文件夹（忽略点文件），存进ArrayList
			for (File f : files) {
				if(!f.getName().startsWith(".")){
				HashMap<String, String> map1 = new HashMap<String, String>();
				map1.put("name", f.getName());
				map1.put("path", f.getPath());
				fileList.add(map1);
				}
			}
		}
		//使用SimpleAdapter作为ListActivity的适配器
		SimpleAdapter sa = new SimpleAdapter(
				//当前类
				this, 
				//要显示的资源
				fileList, 
				//ListActivity的布局文件
				R.layout.list,
				//要显示的每一列的名称（这里只显示一列）
				new String[] { "name" },
				//每一列对应的布局文件
				new int[] { R.id.file_name });
		//为ListActivity设置适配器
		setListAdapter(sa);
	}

	//监听返回键，当用户点击返回键时，返回上层目录
	@Override
	public void onBackPressed() {
		if(currentFile.getPath().equals(rootFile.getPath()))
		super.onBackPressed();
		else {
			currentFile=currentFile.getParentFile();
			showDir(currentFile);
		}
	}
	
	//监听用户选择的文件，若选择的是图片，则显示，若是文件夹，则进入下一层
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//获取用户选择的路径
		String currentPath = fileList.get(position).get("path");
		currentFile = new File(currentPath);
		//如果该路径是文件夹，则进入下一
		if (currentFile.isDirectory()) {
			showDir(currentFile);
		} else {
			//如果是图片，则显示（本例只支持jpg，可以自行添加其他格式）
			if (currentPath.endsWith(".jpg")) {
				//通过Intent传递图片路径
				Intent intent = new Intent();
				intent.putExtra("picPath", currentPath);
				intent.setClass(this, ImageViewerActivity.class);
				this.startActivity(intent);
			} 
		}
	}
	
	
}
