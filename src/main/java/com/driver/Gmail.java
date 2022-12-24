package com.driver;

import java.util.*;



public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    private ArrayList<Mail> InboxMails;
    private ArrayList<Mail> TrashMail;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity =inboxCapacity;
        this.InboxMails = new ArrayList<>();
        this.TrashMail = new ArrayList<>();

    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        Mail newMail = new Mail(date,sender,message);

        if(InboxMails.size()==inboxCapacity){
            Mail oldestMail = InboxMails.get(0);

            InboxMails.remove(oldestMail);
            TrashMail.add(oldestMail);
            InboxMails.add(newMail);
        }else{
            InboxMails.add(newMail);
        }


    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        ListIterator<Mail> iterator = InboxMails.listIterator();
        while(iterator.hasNext()){
            Mail currentMail = iterator.next();
            if(message.equals(currentMail.getMessage())){
                TrashMail.add(currentMail);
                break;
            }
        }
//        for(Mail mail : TrashMail){
//            System.out.println(mail.getMessage());
//        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(InboxMails.size()==0){
            return null;
        }else{
            return InboxMails.get(InboxMails.size()-1).getMessage();

        }

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(InboxMails.size()==0){
            return null;
        }else{
           return InboxMails.get(0).getMessage();

        }

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int startIdx=0;
        int endIdx=0;

        ListIterator<Mail> iterator = InboxMails.listIterator();
        while(iterator.hasNext()){
            Date currentMailDate = iterator.next().getDate();
            if(currentMailDate.equals(start)){
                startIdx=iterator.previousIndex();
                break;
            }
        }
        ListIterator<Mail> iterate = InboxMails.listIterator(startIdx);
        while(iterator.hasNext()){
            Date currentMailDate = iterator.next().getDate();
            if(currentMailDate.equals(end)){
                continue;
            }else{
                endIdx= iterate.previousIndex();
            }
        }
        int totalmailBetween = endIdx-startIdx+1;
        return totalmailBetween;

    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return InboxMails.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash

         return TrashMail.size();
    }

    public void emptyTrash(){

        TrashMail.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
