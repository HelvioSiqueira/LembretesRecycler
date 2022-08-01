// Generated by view binder compiler. Do not edit!
package com.example.lembretesrecycler.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.lembretesrecycler.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LembreteListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView rvLembretes;

  private LembreteListBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView rvLembretes) {
    this.rootView = rootView;
    this.rvLembretes = rvLembretes;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LembreteListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LembreteListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.lembrete_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LembreteListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rvLembretes;
      RecyclerView rvLembretes = ViewBindings.findChildViewById(rootView, id);
      if (rvLembretes == null) {
        break missingId;
      }

      return new LembreteListBinding((ConstraintLayout) rootView, rvLembretes);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}