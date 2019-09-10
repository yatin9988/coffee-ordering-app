package com.example.coffee;

         import android.content.Intent;
         import android.graphics.Color;
         import android.net.Uri;
         import android.os.Bundle;
         import android.support.v7.app.AppCompatActivity;
         import android.view.View;
         import android.widget.CheckBox;
         import android.widget.EditText;
         import android.widget.TextView;
         import android.widget.Toast;

         import java.text.NumberFormat;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String createOrderSummary(int price,boolean val,boolean col,String name){
        String s=getString(R.string.hint)+ " : "  + name+"\n" + getString(R.string.more_cream) + val +"\n"+ getString(R.string.more_chocolate) +col +"\n" + getString(R.string.quantity)+" : " +quantity +"\n" + getString(R.string.total) +" : " + getString(R.string.currency) +price + "\n" +getString(R.string.thanks);
        return s;
    }
    /*
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price=calculatePrice();
        EditText name = (EditText) findViewById(R.id.name);
        CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean val = hasWhippedCream.isChecked();
        boolean col = hasChocolate.isChecked();
        String ss = name.getText().toString();
        String s=createOrderSummary(price,val,col,ss);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "COFFEE ORDER RECEIPT");
        intent.putExtra(Intent.EXTRA_TEXT, s);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayPrice(price);
    }

    private void displayPrice(int number)
    {
         TextView price = (TextView) findViewById(R.id.order_summary_text_view);
         price.setText(getString(R.string.currency) + number);
    }

    public int calculatePrice(){
        int price=0;
        CheckBox cream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        if (cream.isChecked())
        {
            price+=1;
        }
        if(chocolate.isChecked())
        {
            price+=2;
        }
        return quantity*(5+price);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number); //number when concatenated with a string finally becomes a string
    }
    /**
     * This method displays the given price on the screen.
     */
    public void increment(View view) {
        quantity=quantity+1;
        if(quantity>100) {
            quantity = 100;
            //calling a factory method on Toast
            Toast toast = Toast.makeText(this,"100 Coffees is MAX",Toast.LENGTH_SHORT);
            toast.show();
        }
        displayQuantity(quantity);
    }
    public void decrement(View view) {
        quantity=quantity-1;
        if(quantity<1) {
            quantity = 1;
            // calling a factory method on Toast
            Toast toast = Toast.makeText(this,"1 Coffee is Min",Toast.LENGTH_SHORT);
            toast.show();
        }
        displayQuantity(quantity);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
/*
  observations about the displayMesssge function
  the access specifier of this function is public ie it can be accessed from anywhere within the
  program. the return type is void ir it does not return any value.
  it takes only 1 input parameter
 */