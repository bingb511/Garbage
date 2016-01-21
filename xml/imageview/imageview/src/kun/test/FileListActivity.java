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
 * ʹ��ListActivity����ʾSD�����ļ�
 * �û����ڴ�ѡ��Ҫ��ʾ��ͼƬ
 */
public class FileListActivity extends ListActivity {
	
	private File rootFile = null;
	private File currentFile = null;
	private ArrayList<HashMap<String, String>> fileList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��ȡSD����·��
		rootFile = Environment.getExternalStorageDirectory();
		currentFile = rootFile;
		//��ʾ�ļ��б�
		showDir(rootFile);
	}
	
	//���ݴ����File������ʾ��File���ڵ��ļ�
	public void showDir(File pathFile) {
		//ArrayList�����ļ�Ŀ¼��ÿ���ļ���Ŀ��HashMap�ļ������ļ�����ֵ�����ļ�·��
		fileList = new ArrayList<HashMap<String, String>>();
		if (pathFile != null && pathFile.exists()) {
			File[] files = pathFile.listFiles();
			//����Ŀ¼��Ϊ�ļ���Ŀ���û���ѡ�񷵻��ϲ�Ŀ¼
			if (pathFile.getParentFile() != rootFile.getParentFile()) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("name", pathFile.getParentFile().getName());
				map.put("path", pathFile.getParent());
				fileList.add(map);
			}
			//������ǰĿ¼�µ��ļ����ļ��У����Ե��ļ��������ArrayList
			for (File f : files) {
				if(!f.getName().startsWith(".")){
				HashMap<String, String> map1 = new HashMap<String, String>();
				map1.put("name", f.getName());
				map1.put("path", f.getPath());
				fileList.add(map1);
				}
			}
		}
		//ʹ��SimpleAdapter��ΪListActivity��������
		SimpleAdapter sa = new SimpleAdapter(
				//��ǰ��
				this, 
				//Ҫ��ʾ����Դ
				fileList, 
				//ListActivity�Ĳ����ļ�
				R.layout.list,
				//Ҫ��ʾ��ÿһ�е����ƣ�����ֻ��ʾһ�У�
				new String[] { "name" },
				//ÿһ�ж�Ӧ�Ĳ����ļ�
				new int[] { R.id.file_name });
		//ΪListActivity����������
		setListAdapter(sa);
	}

	//�������ؼ������û�������ؼ�ʱ�������ϲ�Ŀ¼
	@Override
	public void onBackPressed() {
		if(currentFile.getPath().equals(rootFile.getPath()))
		super.onBackPressed();
		else {
			currentFile=currentFile.getParentFile();
			showDir(currentFile);
		}
	}
	
	//�����û�ѡ����ļ�����ѡ�����ͼƬ������ʾ�������ļ��У��������һ��
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//��ȡ�û�ѡ���·��
		String currentPath = fileList.get(position).get("path");
		currentFile = new File(currentPath);
		//�����·�����ļ��У��������һ
		if (currentFile.isDirectory()) {
			showDir(currentFile);
		} else {
			//�����ͼƬ������ʾ������ֻ֧��jpg�������������������ʽ��
			if (currentPath.endsWith(".jpg")) {
				//ͨ��Intent����ͼƬ·��
				Intent intent = new Intent();
				intent.putExtra("picPath", currentPath);
				intent.setClass(this, ImageViewerActivity.class);
				this.startActivity(intent);
			} 
		}
	}
	
	
}
