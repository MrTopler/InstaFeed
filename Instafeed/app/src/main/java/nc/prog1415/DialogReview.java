package nc.prog1415;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
//Coded by Jake Terryberry
public class DialogReview extends AppCompatDialogFragment {
    private EditText editTextReview;
    private DialogReviewListener listener;
    private ImageButton ibThumpsUp;
    private ImageButton ibThumpsDown;
    int rate;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_review, null);

        builder.setView(view).setTitle("Review").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String review = editTextReview.getText().toString();
                listener.applyReview(review, rate);
            }
        });

        editTextReview = view.findViewById(R.id.edit_review);

        //Image Buttons
        ibThumpsUp = view.findViewById(R.id.ibThumpsUp);
        ibThumpsUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibThumpsUp.setImageResource(R.drawable.thumbs_up_blue);
                ibThumpsDown.setImageResource(R.drawable.thumbs_down_grey);
                rate = 1;
            }
        });

        ibThumpsDown = view.findViewById(R.id.ibThumpsDown);
        ibThumpsDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibThumpsDown.setImageResource(R.drawable.thumbs_down_blue);
                ibThumpsUp.setImageResource(R.drawable.thumbs_up_grey);
                rate = 0;
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DialogReviewListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogReviewListener");
        }
    }

    public interface DialogReviewListener {
        void applyReview(String review, int rating);
    }
}
