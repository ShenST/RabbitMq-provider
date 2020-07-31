package com.example.rabbitmqprovider.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class MessageLog implements Serializable {
  private String MessageId;
  private String TenantID;
  private String ProcessStage;
  private String ProcessStageStatus;
  private String ExternalTransmission;
  private String InternalTransmission;
  private String SelfBillingDocument;
  private String MessageContent;
}
