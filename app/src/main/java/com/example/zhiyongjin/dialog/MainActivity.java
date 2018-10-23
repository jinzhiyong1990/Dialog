package com.example.zhiyongjin.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);

    }

    public void myclick(View view){
        switch (view.getId()){

            case R.id.putong1:
                showNormalDialog();
                break;
        }
    }

    public void showNormalDialog(){
//         Alertdialog
//        Alertdialog的构造方法是被修饰为protected
//        因此包外是无法使用的, 所以我们利用构建器(Builder)

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

//        设置对话框的标题
        builder.setTitle("提示");

//        设置内容
        builder.setMessage("你是否确定退出当前程序");

//        设置按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                退出当前程序
                MainActivity.this.finish();
            }
        });



        builder.setNegativeButton("取消", null);
//        builder.setNeutralButton();


//        对话框的显示方法
        builder.show();
    }
}
