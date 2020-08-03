package com.example.vo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class DataCollection<T> {
    public List<Contacts> contactsList;
    public List<Ticket> ticketList;
    public List<Agent> agentList;
    public DataCollection(List<Contacts> contactsList, List<Ticket> ticketList, List<Agent> agentList)
    {
        this.agentList=agentList;
        this.contactsList=contactsList;
        this.ticketList=ticketList;
    }
}
