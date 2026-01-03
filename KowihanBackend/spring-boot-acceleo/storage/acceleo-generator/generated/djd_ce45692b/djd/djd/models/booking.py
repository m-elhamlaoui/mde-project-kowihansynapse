from django.db import models
import uuid

class Booking(models.Model):
    id = models.AutoField(primary_key=True)
    startdate = models.DateField(null=True, blank=True)
    enddate = models.DateField(null=True, blank=True)
    customer = models.OneToOneField('Customer', on_delete=models.CASCADE)

    class Meta:
        db_table = 'bookings'

    def __str__(self):
        return f"Booking {self.id}"
