// Generated by view binder compiler. Do not edit!
package com.example.lembretesrecycler.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.lembretesrecycler.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentLembreteFormBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final TextInputEditText edtText;

  @NonNull
  public final TextInputEditText edtTitle;

  @NonNull
  public final ScrollView scrollView;

  @NonNull
  public final Spinner spnPrioridades;

  @NonNull
  public final TextView textAdd;

  @NonNull
  public final TextInputLayout tilText;

  @NonNull
  public final TextInputLayout tilTitle;

  private FragmentLembreteFormBinding(@NonNull ScrollView rootView,
      @NonNull TextInputEditText edtText, @NonNull TextInputEditText edtTitle,
      @NonNull ScrollView scrollView, @NonNull Spinner spnPrioridades, @NonNull TextView textAdd,
      @NonNull TextInputLayout tilText, @NonNull TextInputLayout tilTitle) {
    this.rootView = rootView;
    this.edtText = edtText;
    this.edtTitle = edtTitle;
    this.scrollView = scrollView;
    this.spnPrioridades = spnPrioridades;
    this.textAdd = textAdd;
    this.tilText = tilText;
    this.tilTitle = tilTitle;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentLembreteFormBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentLembreteFormBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_lembrete_form, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentLembreteFormBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edtText;
      TextInputEditText edtText = ViewBindings.findChildViewById(rootView, id);
      if (edtText == null) {
        break missingId;
      }

      id = R.id.edtTitle;
      TextInputEditText edtTitle = ViewBindings.findChildViewById(rootView, id);
      if (edtTitle == null) {
        break missingId;
      }

      ScrollView scrollView = (ScrollView) rootView;

      id = R.id.spnPrioridades;
      Spinner spnPrioridades = ViewBindings.findChildViewById(rootView, id);
      if (spnPrioridades == null) {
        break missingId;
      }

      id = R.id.textAdd;
      TextView textAdd = ViewBindings.findChildViewById(rootView, id);
      if (textAdd == null) {
        break missingId;
      }

      id = R.id.tilText;
      TextInputLayout tilText = ViewBindings.findChildViewById(rootView, id);
      if (tilText == null) {
        break missingId;
      }

      id = R.id.tilTitle;
      TextInputLayout tilTitle = ViewBindings.findChildViewById(rootView, id);
      if (tilTitle == null) {
        break missingId;
      }

      return new FragmentLembreteFormBinding((ScrollView) rootView, edtText, edtTitle, scrollView,
          spnPrioridades, textAdd, tilText, tilTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
