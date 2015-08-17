package io.mbedder.controlcenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class ErrorDialog extends DialogFragment {

    String errorMessage;

    public ErrorDialog() {
        this.errorMessage = "An unspecified error has occurred. This should never happen, so be sure to yell at the devs.";
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.errorDialogTitle).
                setMessage(errorMessage);

        return builder.create();
    }

}
