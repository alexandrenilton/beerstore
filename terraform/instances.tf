resource "aws_key_pair" "keypair" {
  public_key = file("key/beerstore_key.pub")
}


resource "aws_instance" "instances" {
  count = 3

  ami = "ami-062f7200baf2fa504"
  instance_type = "t2.micro"
  subnet_id = element(aws_subnet.public_subnet.*.id, count.index)

  key_name = aws_key_pair.keypair.key_name

  vpc_security_group_ids = [aws_security_group.allow_ssh.id]

//  tags = {
//    Name = "instance"
//  }
}

output "public_ips" {
  value = join(", ", aws_instance.instances.*.public_ip)
}