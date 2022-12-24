package com.driver;

import java.util.*;

import static java.util.Map.entry;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    Map<Date,String> InboxMails;
    Map<Date,String> TrashMail;
    int noOfMailsReceived;
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity =inboxCapacity;
        this.InboxMails = new HashMap<>();
        this.TrashMail = new HashMap<>();
        this.noOfMailsReceived = 0;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.





        if(noOfMailsReceived==inboxCapacity){

//            Date oldestMail = InboxMails.entrySet().stream().findFirst().get().getKey();
//            Date oldestMail = InboxMails.entrySet().stream().reduce((one, two)->two).get().getKey();
//
//            InboxMails.entrySet().removeIf(entry -> (oldestMail == entry.getKey()));

            Date oldestMail;
            List<Date> ls = new ArrayList<>(InboxMails.keySet());
            if(!ls.isEmpty()){
                oldestMail=ls.get(0);
                InboxMails.entrySet().remove(oldestMail);
            }

        }
        InboxMails.put(date,message);
        noOfMailsReceived++;
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        Iterator<Map.Entry<Date,String>> iterator = InboxMails.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Date,String> entry = iterator.next();
            if(message.equals(entry.getValue())){
                TrashMail.put(entry.getKey(),message);
                iterator.remove();
            }
        }


    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(noOfMailsReceived==0){
            return null;
        }else{
//            List<Date> listKeys = new ArrayList<>(InboxMails.keySet());
//            String latestMessage = InboxMails.get(listKeys.get(listKeys.size()-1));
//              String latestMessage = InboxMails.entrySet().stream().reduce((one, two)->two).get().getValue();
              String latestMessage = InboxMails.entrySet().stream().findFirst().get().getValue();
              return latestMessage;
        }


    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(noOfMailsReceived==0){
            return null;
        }else{
            String oldestMessage = InboxMails.entrySet().stream().reduce((one, two)->two).get().getValue();
            return oldestMessage;
        }

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int startIdx=0;
        int endIdx=0;
        List<Date> list = new ArrayList<>(InboxMails.keySet());
        ListIterator<Date> iterator = list.listIterator();
        while(iterator.hasNext()){
            Date date = iterator.next();
            if(date.equals(start)){
                startIdx=iterator.previousIndex();
                break;
            }
        }
        ListIterator<Date> iterate = list.listIterator(startIdx);
        while(iterator.hasNext()){
            Date date = iterator.next();
            if(date.equals(end)){
                continue;
            }else{
                endIdx= iterate.previousIndex();
            }
        }
        int mailBetween = endIdx-startIdx+1;
        return mailBetween;

    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return noOfMailsReceived-1;

    }

    public int getTrashSize(){
        // Return number of mails in Trash

         return TrashMail.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
//        Iterator<Map.Entry<Date,String>> iterator = TrashMail.entrySet().iterator();
//        while(iterator.hasNext()){
////            System.out.println(iterator.next().toString());
//            iterator.remove();
//
//        }
        TrashMail.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
