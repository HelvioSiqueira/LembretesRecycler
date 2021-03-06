// Generated by view binder compiler. Do not edit!
package com.example.lembretesrecycler.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.lembretesrecycler.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final FloatingActionButton fadAdd;

  @NonNull
  public final FragmentContainerView fragmentList;

  private ActivityMainBinding(@NonNull CoordinatorLayout rootView,
      @NonNull FloatingActionButton fadAdd, @NonNull FragmentContainerView fragmentList) {
    this.rootView = rootView;
    this.fadAdd = fadAdd;
    this.fragmentList = fragmentList;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fadAdd;
      FloatingActionButton fadAdd = ViewBindings.findChildViewById(rootView, id);
      if (fadAdd == null) {
        break missingId;
      }

      id = R.id.fragmentList;
      FragmentContainerView fragmentList = ViewBindings.findChildViewById(rootView, id);
      if (fragmentList == null) {
        break missingId;
      }

      return new ActivityMainBinding((CoordinatorLayout) rootView, fadAdd, fragmentList);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
