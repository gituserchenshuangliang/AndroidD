package com.csl.demo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
/**
 * 
 * 主活动
 * @author Cherry
 * @date  2019年6月10日
 */
public class MainActivity extends BaseActivity {

	private ListView msgListView;
	private EditText inputText;
	private MsgAdapter adapter;
	private List<Msg> msgList = new ArrayList<Msg>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * 设置标题不可见
		 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/*
		 * 加载主活动
		 */
		setContentView(R.layout.activity_main);
		/*
		 * 初始化消息数据
		 */
		initMsgs(); 
	}
	
	
	@Override
	public void onClick(View v) {
		String content = inputText.getText().toString();
		switch (v.getId()) {
		case R.id.send:
			if("" != content){
				Msg msg = new Msg(content, Msg.TYPE_SENT);
				msgList.add(msg);
				adapter.notifyDataSetChanged();
				msgListView.setSelection(msgList.size());
				inputText.setText("");
			}
			break;
		case R.id.recieve:
			if("" != content){
				Msg msg = new Msg(content, Msg.TYPE_RECEIVED);
				msgList.add(msg);
				adapter.notifyDataSetChanged();
				msgListView.setSelection(msgList.size());
				inputText.setText("");
			}
			break;
		default:
			break;
		}
	}
	
	private void initMsgs() {
		/*
		 * 加载ListView子布局到适配器
		 */
		adapter = new MsgAdapter(MainActivity.this, R.layout.msg_item, msgList);
		/*
		 * 加载布局
		 */
		msgListView = (ListView) findViewById(R.id.msg_list_view);
		msgListView.setAdapter(adapter);
		/*
		 * 创建按钮点击事件
		 */
		inputText = (EditText) findViewById(R.id.input_text);
		this.createListenButton(R.id.send);
		this.createListenButton(R.id.recieve);
	}
}