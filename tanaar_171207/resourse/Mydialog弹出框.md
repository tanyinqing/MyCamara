


- 提示信息的弹出框

```
MyDialog myDialog=new MyDialog(ReceActivity.this)
  .setTitle("提示")
  .setMessage("发现可合并订单，是否合并？")
  .setPositiveButton(R.string.ok2, new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          intent.putExtra("prochsn", merge_orderSn);
          intent.putExtra("is_merge", "2");
          startActivity(intent);
      }
  })
  .setNegativeButton(R.string.cance2, new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          intent.putExtra("prochsn", prochsn);
          intent.putExtra("category", "1");
          startActivity(intent);
      }
  });
  myDialog.create(null).show();
```
- 输入信息的弹出框
```
 final  MyDialog dialog = new MyDialog(PorchInfoActivity.this);
            final  EditText editText=dialog.getCurrentEditText();
             dialog .setTitle("提示")
                    .setEditText("请输入拒绝的理由!", InputType.TYPE_TEXT_FLAG_MULTI_LINE, new InputFilter[]{new InputFilter.LengthFilter(100)})
                    .setPositiveButton(R.string.ok1,
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View arg0) {
                                    mStatisWeb.proch_reject(prochsn, editText.getText().toString().trim(), handler);
                                }
                            });
            dialog.setNegativeButton(R.string.cancel,
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            dialog.dismiss();
                        }
                    });
            dialog.create(null).show();
```