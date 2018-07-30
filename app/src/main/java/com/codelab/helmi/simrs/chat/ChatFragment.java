package com.codelab.helmi.simrs.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.api.SharedPrefManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatFragment extends Fragment {

    private Button btn_send;
    private EditText et_content;
    @SuppressLint("StaticFieldLeak")
    public static ChatAdapter mAdapter;

    private ListView listview;
    private List<ChatMessage> items = new ArrayList<>();
    ParseFirebaseData pfbd;

    String chatNode,hostID, token;

    public static final String MESSAGE_CHILD = "messages";
    DatabaseReference ref;
    SharedPrefManager pref;
    private TextView empty;
    private Button leave;

    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chats, container, false);
        initView();
        return view;
    }

    public void initView(){

        View parent_view = view.findViewById(android.R.id.content);
        setHasOptionsMenu(true);
        pfbd = new ParseFirebaseData(getActivity());
        pref = new SharedPrefManager(getContext());
        token = FirebaseInstanceId.getInstance().getToken();

        hostID = "RS";
        leave = view.findViewById(R.id.leave);

        listview = view.findViewById(R.id.listview);
        empty = view.findViewById(R.id.empty);
        btn_send = view.findViewById(R.id.btn_send);
        et_content = view.findViewById(R.id.text_content);
        chatNode = pref.getSpNoRm() + "-" + hostID;

        ref = FirebaseDatabase.getInstance().getReference(MESSAGE_CHILD);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String totalData;
                if(dataSnapshot.child(chatNode).exists()) {
                    totalData = dataSnapshot.child(chatNode).toString();
                    items.clear();
                    items.addAll(pfbd.getMessageListForUser(totalData));
                    leave.setVisibility(View.VISIBLE);
                    empty.setVisibility(View.GONE);
                    mAdapter = new ChatAdapter(getContext(), items);
                    listview.setAdapter(mAdapter);
                    listview.setSelectionFromTop(mAdapter.getCount(), 0);
                    listview.requestFocus();
                    registerForContextMenu(listview);
                    try {
                        mAdapter.notifyDataSetChanged();
                        listview.setSelectionFromTop(mAdapter.getCount(), 0);
                    } catch (Exception ignored) {
                    }
                }else{
                    items.clear();
                    leave.setVisibility(View.GONE);
                    empty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Tidak dapat terhubung", Toast.LENGTH_SHORT).show();
            }
        });

        leave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Hapus chat?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ref.child(chatNode).removeValue();
                            }
                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });

        btn_send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> hm = new HashMap<>();
                hm.put("timestamp", ServerValue.TIMESTAMP);
                hm.put("receiverid", hostID);
                hm.put("text", pfbd.encodeText(et_content.getText().toString()));
                hm.put("senderid", pref.getSpNoRm());
                hm.put("token", token);

                ref.child(chatNode).push().setValue(hm);
                et_content.setText("");
                hideKeyboard();
                new Notification().send("/topics/rskasihibu");
            }
        });
        et_content.addTextChangedListener(contentWatcher);
        if (et_content.length() == 0) {
            btn_send.setEnabled(false);
        }
        hideKeyboard();
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private TextWatcher contentWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable etd) {
            if (etd.toString().trim().length() == 0) {
                btn_send.setEnabled(false);
            } else {
                btn_send.setEnabled(true);
            }
            //draft.setContent(etd.toString());
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }
    };


}
