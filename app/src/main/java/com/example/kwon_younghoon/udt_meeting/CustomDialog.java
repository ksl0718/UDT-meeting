package com.example.kwon_younghoon.udt_meeting;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomDialog extends Dialog {

    private TextView mTitleView;
    private Button mCloseButton;
    private Button mRightButton;
    private String mTitle;
    ListView listView = null ;
    ArrayList<FriendData> arrayList = new ArrayList<FriendData>();
    FriendAdapter friendAdapter;
    private Context context;
    private View.OnClickListener mCloseClickListener;
    private View.OnClickListener mRightClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_custom_dialog);


        init();

        EditText editTextFilter = (EditText)findViewById(R.id.friends_search) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                ((FriendAdapter)listView.getAdapter()).getFilter().filter(filterText);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        }) ;


        mTitleView = (TextView) findViewById(R.id.txt_title);
        mCloseButton = (Button) findViewById(R.id.btn_close);
        mRightButton = (Button) findViewById(R.id.btn_right);

        // 제목과 내용을 생성자에서 셋팅한다.
        mTitleView.setText(mTitle);

        // 클릭 이벤트 셋팅
        if (mCloseClickListener != null && mRightClickListener != null) {
            mCloseButton.setOnClickListener(mCloseClickListener);
            mRightButton.setOnClickListener(mRightClickListener);
        } else if (mCloseClickListener != null
                && mRightClickListener == null) {
            mCloseButton.setOnClickListener(mCloseClickListener);
        } else {

        }
    }

    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public CustomDialog(Context context, String title,
                        View.OnClickListener singleListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mCloseClickListener = singleListener;

    }

    // 클릭버튼이 확인과 취소 두개일때 생성자 함수로 이벤트를 받는다
    public CustomDialog(Context context, String title
            , View.OnClickListener closeListener,
                        View.OnClickListener rightListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mCloseClickListener = closeListener;
        this.mRightClickListener = rightListener;
        this.context = context;
    }

    void init(){
        listView = (ListView)findViewById(R.id.in_friends_list);
        friendAdapter = new FriendAdapter();
        friendAdapter.addItem(R.drawable.a, "gegnnjr", "geg", "15");
        friendAdapter.addItem(R.drawable.a, "herweqr", "영훈", "16");
        friendAdapter.addItem(R.drawable.a, "hfsagnjr", "지윤", "15");
        friendAdapter.addItem(R.drawable.a, "hgaer", "영훈", "16");
        friendAdapter.addItem(R.drawable.a, "jadhar", "지윤", "15");
        friendAdapter.addItem(R.drawable.a, "hbvzxqr", "영훈", "16");
        listView.setAdapter(friendAdapter);
    }
}