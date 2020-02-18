resource "aws_security_group" "allow_ssh" {
  vpc_id = aws_vpc.main.id
  name = "cloudvisor_allow_ssh"

  ingress {
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["45.5.157.117/32"] // curl -s ipinfo.io/ip, sempre /32
  }
}

resource "aws_security_group" "database" {
  vpc_id = aws_vpc.main.id
  name = "beerstore_database"

  ingress {
    from_port = 5432
    to_port = 5432
    protocol = "tcp"
    self = true
  }
}

resource "aws_security_group" "allow_outbound" {
  vpc_id = aws_vpc.main.id
  name = "cloudvisor_allow_outbound"

  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}