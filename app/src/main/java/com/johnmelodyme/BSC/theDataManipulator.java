package com.johnmelodyme.BSC;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class theDataManipulator {
    private FirebaseDatabase BSC_REGISTRATION_DATABASE;
    private DatabaseReference BSC_REGISTRATION_REFERENCE;
    private List<realtimedata> BSC_RES = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<realtimedata>BSC_RES, List<String>keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public theDataManipulator(){
        BSC_REGISTRATION_DATABASE = FirebaseDatabase.getInstance();
        BSC_REGISTRATION_REFERENCE = BSC_REGISTRATION_DATABASE.getReference("bloodsugarco-c94c1");
    }

    public void read_data(final DataStatus dataStatus){
        BSC_REGISTRATION_REFERENCE.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BSC_RES.clear();
                List<String> keys  = new ArrayList<>();
                for(DataSnapshot keynode : dataSnapshot.getChildren()){
                    realtimedata RD;
                    keys.add(keynode.getKey());
                    RD = keynode.getValue(realtimedata.class);
                    BSC_RES.add(RD);
                }
                dataStatus.DataIsLoaded(BSC_RES, keys);
            }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
}

    public void addData(realtimedata rd, final DataStatus ds){
        String key;
        key = BSC_REGISTRATION_REFERENCE.push().getKey();
        BSC_REGISTRATION_REFERENCE.child(key).setValue(rd)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        ds.DataIsInserted();
                    }
                });
    }
}
