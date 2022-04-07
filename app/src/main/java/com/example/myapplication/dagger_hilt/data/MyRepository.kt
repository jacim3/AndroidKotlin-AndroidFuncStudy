package com.example.myapplication.dagger_hilt.data

import javax.inject.Inject
import javax.inject.Singleton


/*
    ApplicationComponent	    Application
    ActivityRetainedComponent	ViewModel
    ActivityComponent	        Activity
    FragmentComponent	        Fragment
    ViewComponent	            View
    ViewWithFragmentComponent	@WithFragmentBindings 주석이 지정된 View
    ServiceComponent	        Service
*/

/*
    클래스                                   컴포넌트                          범위
    Application(전체 앱)	                    ApplicationComponent	        @Singleton
    ViewModel	                            ActivityRetainedComponent	    @ActivityRetainedScope
    Activity(해당액티비와 하위 프래그먼트)       ActivityComponent	            @ActivityScoped
    Fragment	                            FragmentComponent       	    @FragmentScoped
    View                    	            ViewComponent               	@ViewScoped
    @WithFragmentBindings 주석이 지정된 View	ViewWithFragmentComponent	    @ViewScoped
    Service	                                ServiceComponent	            @ServiceScoped
*/
@Singleton
class MyRepository @Inject constructor(){
}