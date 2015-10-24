package de.vogel.winetasteic.winetasteic;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChallengeSuccessFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChallengeSuccessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChallengeSuccessFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "index";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int index;
    private String mParam2;
    VideoView videoView;
    Button buttonDone;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChallengeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChallengeSuccessFragment newInstance(int param1, String param2) {
        ChallengeSuccessFragment fragment = new ChallengeSuccessFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ChallengeSuccessFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_challenge_success, container, false);
        buttonDone =(Button)view.findViewById(R.id.btn_done);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mParam2 == "1"){
                    mListener.switchPage();
                }else {
                    mListener.finishChallenge();
                }

            }
        });
        videoView = (VideoView)view.findViewById(R.id.videoView);
        String uriPath = "";
        if(mParam2 == "1"){
             uriPath = "android.resource://"+getActivity().getPackageName()+"/" +R.raw.my;
        }else{
             uriPath = "android.resource://"+getActivity().getPackageName()+"/" +R.raw.my2;
        }

        Uri uri = Uri.parse(uriPath);
        videoView.setVideoURI(uri);
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                videoView.start();
                return true;
            }
        });
        videoView.seekTo(100);
       // videoView.start();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.finishChallenge();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
       public void finishChallenge();
        public void switchPage();
    }

}
