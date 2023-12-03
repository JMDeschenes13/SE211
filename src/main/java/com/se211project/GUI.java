package com.se211project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;

import com.se211project.Main;


public class GUI {
    private JFrame frame;
    private Main main;
    private JList<String> reservationsList;
    private DefaultListModel<String> reservationsListModel;
    private JList<String> serviceBookingsList;
    private DefaultListModel<String> serviceBookingsListModel;
    private JComboBox<String> createReservationSelectRoom;
    private DefaultComboBoxModel<String> createReservationSelectRoomModel;
    private JComboBox<String> createReservationSelectGuest;
    private DefaultComboBoxModel<String> createReservationSelectGuestModel;
    private JScrollPane reservationsListScrollPane;


    public GUI(){
        createFrame();



    }
    

    private JFrame createFrame(){
        frame = new JFrame("Hotel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container mainPane = frame.getContentPane();
        mainPane.setLayout(null);
        createMainContainer(mainPane);
        

       //frame.pack();
        frame.setSize(1920, 1080);
        
        
        frame.setVisible(true);
        return frame;

    }

    

    private void createMainContainer(Container mainPane){
        // Creating all the panels
        JPanel westPanel = new JPanel(new BorderLayout());
        JPanel eastPanel = new JPanel(new BorderLayout());
        JPanel roomsPanel = new JPanel(new GridLayout(2,1));
        JPanel servicesPanel = new JPanel(new GridLayout(2,1));
        JPanel reservationLookupPanel = new JPanel(new GridLayout(4,1));
        JPanel serviceBookingLookupPanel = new JPanel(new GridLayout(4,1));
        JPanel createReservationPanel = new JPanel(new BorderLayout());
        JPanel createServiceBookingPanel = new JPanel(new BorderLayout());
    

        // Creating all the buttons
        JButton buttonReservationLookup = createButton("Look Up", 100, 100);
        JButton buttonServiceBookingLookup = createButton("Look Up", 100, 100);
        JButton buttonCreateReservation = createButton("Create", 100, 100);
        JButton buttonCreateServiceBooking = createButton("Create", 100, 100);
        JButton buttonSearchReservationDates = createButton("Search", 100, 100);

        // Creating most of the text boxes
        JTextField textTitle = new JTextField("Hotel Management System");
        JTextField textRoomsTitle = new JTextField("Available Rooms");
        JTextField textServicesTitle = new JTextField("Available Services");
        JTextField textReservationLookupTitle = new JTextField("Reservation Lookup");
        JTextField textCreateReservation = new JTextField("Create Reservation");
        JTextField textCreateServiceBooking = new JTextField("Create Service Booking");
        //JTextField inputReservationLookup = new JTextField("Enter Guest ID: ");
        //JTextField inputServiceBookingLookup = new JTextField("Enter Guest ID: ");
        JTextField textServiceBookingLookupTitle = new JTextField("Service Booking Lookup");


        // Arrays of Guest and Services used for the Guest ans Service dropdown menus
        String[] guestList = main.getGuestListData();
        String[] serviceList = main.getServiceListData();

        // Creating the fonts
        Font fontTitle = new Font("fontTitle", 137, 48);
        Font fontMedium= new Font("fontMedium", 137, 32);


        /* 
        textTitle.setEditable(false);
        textTitle.setFont(fontTitle);
        textTitle.setHorizontalAlignment(JTextField.CENTER);
        */


        // Creating title text fields
        textRoomsTitle.setEditable(false);
        textRoomsTitle.setFont(fontTitle);
        textRoomsTitle.setHorizontalAlignment(JTextField.CENTER);
        textRoomsTitle.setPreferredSize(new Dimension(500,100));

        textServicesTitle.setEditable(false);
        textServicesTitle.setFont(fontTitle);
        textServicesTitle.setHorizontalAlignment(JTextField.CENTER);

        textReservationLookupTitle.setEditable(false);
        textReservationLookupTitle.setFont(fontTitle);
        textReservationLookupTitle.setHorizontalAlignment(JTextField.CENTER);

        //inputReservationLookup.setEditable(true);
        //inputReservationLookup.setFont(fontMedium);
        //inputReservationLookup.setHorizontalAlignment(JTextField.LEFT);

        textCreateReservation.setEditable(false);
        textCreateReservation.setFont(fontMedium);
        textCreateReservation.setHorizontalAlignment(JTextField.CENTER);

        textServiceBookingLookupTitle.setEditable(false);
        textServiceBookingLookupTitle.setFont(fontTitle);
        textServiceBookingLookupTitle.setHorizontalAlignment(JTextField.CENTER);

        textCreateServiceBooking.setEditable(false);
        textCreateServiceBooking.setFont(fontMedium);
        textCreateServiceBooking.setHorizontalAlignment(JTextField.CENTER);
        



        
        reservationsListScrollPane = new JScrollPane();
        reservationsListModel = new DefaultListModel<String>();
        addDefaultListData(reservationsListModel);
        reservationsList = new JList<String>();

        JComboBox<String> dropdownReservationLookup = new JComboBox<String>();
        DefaultComboBoxModel<String> dropdownReservationLookupModel = new DefaultComboBoxModel<String>();
        dropdownReservationLookup.setModel(dropdownReservationLookupModel);
        for(int i = 0; i < guestList.length; i++){
            dropdownReservationLookupModel.addElement(guestList[i]);
        };

        configList(reservationsList);
        //reservationsList.setPreferredSize(new Dimension(700,100));
        reservationsList.setModel(reservationsListModel);
        reservationsListScrollPane.setViewportView(reservationsList);
        reservationLookupPanel.add(textReservationLookupTitle);
        reservationLookupPanel.add(dropdownReservationLookup);
        reservationLookupPanel.add(buttonReservationLookup);
        reservationLookupPanel.add(reservationsListScrollPane);



        
        JComboBox<String> dropdownServiceBookingLookup = new JComboBox<String>();
        DefaultComboBoxModel<String> dropdownServiceBookingLookupModel = new DefaultComboBoxModel<String>();
        dropdownServiceBookingLookup.setModel(dropdownServiceBookingLookupModel);
        for(int i = 0; i < guestList.length; i++){
            dropdownServiceBookingLookupModel.addElement(guestList[i]);
        }
        serviceBookingsListModel = new DefaultListModel<String>();
        serviceBookingsList = new JList<String>();
        configList(serviceBookingsList);
        //serviceBookingsList.setPreferredSize(new Dimension(700,100));
        serviceBookingsList.setModel(serviceBookingsListModel);
        serviceBookingLookupPanel.add(textServiceBookingLookupTitle);
        serviceBookingLookupPanel.add(dropdownServiceBookingLookup);
        serviceBookingLookupPanel.add(buttonServiceBookingLookup);
        serviceBookingLookupPanel.add(serviceBookingsList);


        servicesPanel.add(textServicesTitle);
        JScrollPane servicesScrollPane = new JScrollPane();
        JList<String> servicesList = getServicesList();
        servicesScrollPane.setViewportView(servicesList);
        servicesPanel.add(servicesScrollPane);


        roomsPanel.add(textRoomsTitle);
        JScrollPane roomsScrollPane = new JScrollPane();
        //roomsScrollPane.setMaximumSize(new Dimension(500,100));
        JList<String> roomsList = getRoomsList();
        roomsScrollPane.setViewportView(roomsList);
        roomsPanel.add(roomsScrollPane);
       


        JPanel selectReservationDatesPanel = new JPanel(new GridLayout(1,5));
        JTextArea textStartDate = new JTextArea("Check In\n Date");
        textStartDate.setEditable(false);
        textStartDate.setFont(fontMedium);
        //textStartDate.setHorizontalAlignment(JTextField.CENTER);
        JTextArea textEndDate = new JTextArea("Check Out\nDate");
        textEndDate.setEditable(false);
        textEndDate.setFont(fontMedium);
        //textEndDate.setHorizontalAlignment(JTextField.CENTER);

        JXDatePicker startDate = new JXDatePicker();
        JXDatePicker endDate = new JXDatePicker();
        startDate.setDate(Calendar.getInstance().getTime());
        startDate.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
        endDate.setDate(Calendar.getInstance().getTime());
        endDate.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
        startDate.setPreferredSize(new Dimension(50,50));
        endDate.setPreferredSize(new Dimension(50,50));
        selectReservationDatesPanel.add(textStartDate);
        selectReservationDatesPanel.add(startDate);
        selectReservationDatesPanel.add(textEndDate);
        selectReservationDatesPanel.add(endDate);
        selectReservationDatesPanel.add(buttonSearchReservationDates);


        createReservationSelectRoom = new JComboBox<String>();
        createReservationSelectRoomModel = new DefaultComboBoxModel<String>();
        createReservationSelectRoom.setModel(createReservationSelectRoomModel);

        createReservationSelectGuest = new JComboBox<String>();
        createReservationSelectGuestModel = new DefaultComboBoxModel<String>();
        createReservationSelectGuest.setModel(createReservationSelectGuestModel);
        for(int i = 0; i < guestList.length; i++){
            createReservationSelectGuestModel.addElement(guestList[i]);
        }

        createReservationPanel.add(textCreateReservation, BorderLayout.NORTH);
        createReservationPanel.add(selectReservationDatesPanel, BorderLayout.WEST);
        createReservationPanel.add(buttonCreateReservation, BorderLayout.SOUTH);
        createReservationPanel.add(createReservationSelectRoom, BorderLayout.CENTER);
        createReservationPanel.add(createReservationSelectGuest, BorderLayout.EAST);

        JComboBox<String> createServiceBookingSelectGuest = new JComboBox<String>();
        DefaultComboBoxModel<String> createServiceBookingSelectGuestModel = new DefaultComboBoxModel<String>();
        createServiceBookingSelectGuest.setModel(createServiceBookingSelectGuestModel);
        for(int i = 0; i < guestList.length; i++){
            createServiceBookingSelectGuestModel.addElement(guestList[i]);
        }
        JComboBox<String> createServiceBookingSelectService = new JComboBox<String>();
        DefaultComboBoxModel<String> createServiceBookingSelectServiceModel = new DefaultComboBoxModel<String>();
        createServiceBookingSelectService.setModel(createServiceBookingSelectServiceModel);
        for(int i = 0; i < serviceList.length; i++){
            createServiceBookingSelectServiceModel.addElement(serviceList[i]);
        }

        createServiceBookingPanel.add(textCreateServiceBooking, BorderLayout.NORTH);
        createServiceBookingPanel.add(createServiceBookingSelectGuest, BorderLayout.WEST);
        createServiceBookingPanel.add(createServiceBookingSelectService, BorderLayout.EAST);
        createServiceBookingPanel.add(buttonCreateServiceBooking, BorderLayout.SOUTH);

    


    
        
        
       

       
        buttonReservationLookup.addActionListener(e -> buttonReservationLookupClick(dropdownReservationLookup.getSelectedItem().toString()));
        buttonServiceBookingLookup.addActionListener(e -> buttonServiceBookingLookupClick(dropdownServiceBookingLookup.getSelectedItem().toString()));
        buttonSearchReservationDates.addActionListener(e -> buttonSearchReservationDatesClick(startDate, endDate));
        buttonCreateServiceBooking.addActionListener(e -> buttonCreateServiceBookingClick(createServiceBookingSelectGuest.getSelectedItem().toString(), createServiceBookingSelectService.getSelectedItem().toString()));
        buttonCreateReservation.addActionListener(e -> buttonCreateReservationClick(startDate, endDate, createReservationSelectRoom.getSelectedItem().toString(), createReservationSelectGuest.getSelectedItem().toString()));

      


        //JPanel lookupPanel = new JPanel(new GridLayout(1,2));
        //JPanel createPanel = new JPanel(new GridLayout(1,2));
       // lookupPanel.add(reservationLookupPanel);
        //lookupPanel.add(serviceBookingLookupPanel);
        //westPanel.add(roomsPanel, BorderLayout.CENTER);
        //westPanel.add(servicesPanel, BorderLayout.SOUTH);
        //mainPane.add(westPanel, BorderLayout.WEST);
    
        //mainPane.add(textTitle, BorderLayout.NORTH);

       // mainPane.add(lookupPanel, BorderLayout.CENTER);
        //createServiceBookingPanel.setSize(new Dimension(500,100));
        //createPanel.add(createReservationPanel);
        createReservationPanel.setBounds(0,0,1350,250);
        createServiceBookingPanel.setBounds(1220,260,600,250);
        roomsPanel.setBounds(0,260,500,400);
        servicesPanel.setBounds(0,670,500,350);
        reservationLookupPanel.setBounds(510,260,700,400);
        serviceBookingLookupPanel.setBounds(510,670,700,350);
    

        mainPane.add(servicesPanel);
        mainPane.add(roomsPanel);

        mainPane.add(createServiceBookingPanel);
        mainPane.add(createReservationPanel);
        mainPane.add(reservationLookupPanel);
        mainPane.add(serviceBookingLookupPanel);
    


         
        


    }


    private JList<String> getRoomsList(){
        String[] roomData = main.getRoomListData();
        

        

        JList<String> list = new JList<String>(roomData);
        
        configList(list);

        
        

        return list;
        
    }

    private JList<String> getServicesList(){


        String[] serviceData = main.getServiceListData();
        
        JList<String> list = new JList(serviceData);
        
        configList(list);

        return list;

    }


    private void configList(JList<String> list){


        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setFixedCellHeight(30);
        list.setFixedCellWidth(100);
        list.setFont(new Font("font", Font.PLAIN, 15));
        list.setForeground(Color.CYAN);
        list.setBackground(Color.BLACK);
        list.setSelectionBackground(Color.CYAN);
        list.setSelectionForeground(Color.BLACK);
        list.setOpaque(true);
        list.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
        list.setFocusable(true);
        list.setAutoscrolls(true);
        list.setDragEnabled(true);

    }




    private JButton createButton(String caption, int width, int height){
        JButton button = new JButton(caption);
        button.setPreferredSize(new Dimension(width, height));
        
        return button;
    }


    private void buttonReservationLookupClick(String GuestID){
        
        int GuestIDInt = Integer.parseInt(GuestID.substring(10,11));
        getReservationsList(GuestIDInt);
        
    }

    private void buttonServiceBookingLookupClick(String GuestID){

        int GuestIDInt = Integer.parseInt(GuestID.substring(10,11));
        getServiceBookingsList(GuestIDInt);
    }


    private void buttonSearchReservationDatesClick(JXDatePicker startDate, JXDatePicker endDate){

        getRoomsAvailableOnDate(startDate, endDate);



    }


    private void getReservationsList(int GuestID){

        String[] reservationData = main.getReservationListData(GuestID);
        reservationsListModel.clear();
        for(int i = 0; i < reservationData.length; i++){

            reservationsListModel.addElement(reservationData[i]);


        }
        reservationsListScrollPane.updateUI();

    }


    private void getServiceBookingsList(int GuestID){
        String[] serviceData = main.getServiceBookingListData(GuestID);
        serviceBookingsListModel.clear();
        for(int i = 0; i < serviceData.length; i++){

            serviceBookingsListModel.addElement(serviceData[i]);


        }

    }

    private String[] getRoomsAvailableOnDate(JXDatePicker startDate, JXDatePicker endDate){
        String startDateString = dateToCorrectFormat(startDate);
        String endDateString = dateToCorrectFormat(endDate);
        
        String[] roomsAvailableData = main.getRoomsAvailableData(startDateString, endDateString);
        createReservationSelectRoomModel.removeAllElements();;
        for(int i = 0; i < roomsAvailableData.length; i++){

            createReservationSelectRoomModel.addElement(roomsAvailableData[i]);


        }
       

        return roomsAvailableData;

    }

    private void buttonCreateReservationClick(JXDatePicker startDate, JXDatePicker endDate, String RoomData, String GuestName){
        String startDateString = dateToCorrectFormat(startDate);
        String endDateString = dateToCorrectFormat(endDate);
        String roomID = RoomData.substring(13,16);
        String guestID = GuestName.substring(10,11);
       
        
        main.createReservation(startDateString, endDateString, roomID, guestID);
        JOptionPane.showMessageDialog(null, "Reservation Created");
      

    }

    private String dateToCorrectFormat(JXDatePicker date){
         HashMap<String, String> monthNumMap = new HashMap<String, String>();
        monthNumMap.put("Jan", "01");
        monthNumMap.put("Feb", "02");
        monthNumMap.put("Mar", "03");
        monthNumMap.put("Apr", "04");
        monthNumMap.put("May", "05");
        monthNumMap.put("Jun", "06");
        monthNumMap.put("Jul", "07");
        monthNumMap.put("Aug", "08");
        monthNumMap.put("Sep", "09");
        monthNumMap.put("Oct", "10");
        monthNumMap.put("Nov", "11");
        monthNumMap.put("Dec", "12");
        String dateString = date.getDate().toString();
        dateString = dateString.substring(24,28) + "-" + monthNumMap.get(dateString.substring(4,7)) + "-" + dateString.substring(8,10) + " 00:00:00";
        return dateString;

    }

    private void addDefaultListData(DefaultListModel<String> model){
    String[] data = {"","","","",""};
        for(int i = 0; i < data.length; i++){
            model.addElement(data[i]);
        }
    
    
    }   

    private void buttonCreateServiceBookingClick(String GuestInfo, String ServiceInfo){
        String guestID = GuestInfo.substring(10,11);
        String serviceID = ServiceInfo.substring(12,15);

        main.createServiceBooking(guestID, serviceID);
        JOptionPane.showMessageDialog(null, "Service Booking Created");





    }


}
