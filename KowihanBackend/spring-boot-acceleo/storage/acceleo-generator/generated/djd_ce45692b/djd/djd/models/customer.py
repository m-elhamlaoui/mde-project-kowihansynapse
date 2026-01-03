from django.db import models
import uuid

class Customer(models.Model):
    idcustomer = models.AutoField(primary_key=True)
    firstname = models.CharField(max_length=255, null=True, blank=True)
    lastname = models.CharField(max_length=255, null=True, blank=True)

    class Meta:
        db_table = 'customers'

    def __str__(self):
        return f"Customer {self.idcustomer}"
